import psycopg2
import base64
import json

# Load your JSON data
with open('juice.json') as f:
    data = json.load(f)
    json = data[0][1]
    # print(json['cost'])
    # print(data[0][1])

# Establish a connection to the PostgreSQL database
conn = psycopg2.connect(
    dbname="infraapi",
    user="postgres",
    password="postgres",
    host="localhost",
    port="5455"
)

# Create a cursor object
cur = conn.cursor()

# Create an INSERT INTO SQL query
for item in data:
    product_id = item[0]
    product = item[1]['product']
    #print(product)
    isle = base64.b64decode(product['departmentId']).decode('utf-8')
    #print(isle)
    #
    cur.execute('SET search_path TO products')
    
    cur.execute('select * from "products"."product"')
    rows = cur.fetchall()
    if not rows:
        print("Table 'cidr' doesn't exist in schema 'products'")
    else:
        print("Table 'cidr' exists in schema 'products'")
    query = """
    INSERT INTO products."product"
    (id, title, brandname, gtin, defaultimageurl, aisleid, shelfid, aislename, shelfname, price, unitprice, unitofmeasure, status)
    VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """
    #Execute the SQL query
    cur.execute(query, (product_id, product['title'], product['brandName'], product['gtin'],
                         product['defaultImageUrl'], product['aisleId'], product['shelfId'], product['aisleName'], product['shelfName'],
                           product['price'], product['unitPrice'], product['unitOfMeasure'], product['status']))
    
    promotionsArr = item[1]['promotions']
    if promotionsArr.__len__() > 0:
        for promotion in promotionsArr:
            startDate = promotion['startDate']
            endDate = promotion['endDate']
            price = promotion['price']
            print(promotion)
            beforeDiscount = ''
            afterDiscount = ''
            if(price is not None):
                beforeDiscount = price['beforeDiscount']
                afterDiscount = price['afterDiscount']
            promotionQry = f"""
            INSERT INTO products.promotions
            (promotionid, promotiontype, startdate, enddate, unitsellinginfo, offertext, beforediscount, afterdiscount, "attributes", productid)
            VALUES(%s, %s, '{startDate}', '{endDate}', %s, %s, %s, %s, %s, %s)
            """
            cur.execute(promotionQry, (promotion['promotionId'], promotion['promotionType'], promotion['unitSellingInfo'], promotion['offerText'],
                                        beforeDiscount, afterDiscount, promotion['attributes'][0], product_id))
    
# Commit the transaction
conn.commit()

# Close the cursor and connection
cur.close()
conn.close()