import pickle
import pandas as pd
from flask import Flask,request,jsonify

app = Flask(__name__)

@app.route("/getPrice",methods=["GET"])
def get_price():
    data = {}
    headers = request.headers
    data["Company"] = [headers.get("Company")]
    data["Country of Origin"] = [headers.get("Country")]
    data["Horsepower (hp)"] = [float(headers.get("Horsepower"))]
    data["Torque (nm)"] = [float(headers.get("Torque"))]
    data["Number of Seating"] = [int(headers.get("SeatingCapacity"))]
    data["Year"] = [int(headers.get("Year"))]
    data["Looks"] = [headers.get("Looks")]
    data["Body Type"] = [headers.get("BodyType")]
    data["Number of Cylinders"] = [int(headers.get("Cylinders"))]
    data["Electric Vehicle"] = [int(headers.get("EV"))]
    data["Engine Displacement"] = [float(headers.get("EngineDisplacement"))]
    data["Motor Power"] = [float(headers.get("MotorPower"))]

    df = pd.DataFrame(data)
    categorical = ["Company", "Country of Origin", "Looks", "Body Type"]
    fin = open("onehot_encoder.pkl","rb")
    encoder = pickle.load(fin)
    fin.close()
    onehot_columns = pd.DataFrame(encoder.transform(df[categorical]))
    # Adding Index
    onehot_columns.index = df.index
    # Getting Numerical Column
    numerical_columns = df.drop(categorical, axis=1)
    # Concatenating DataFrames
    df_onehot = pd.concat([numerical_columns, onehot_columns], axis=1)
    df_onehot.columns = df_onehot.columns.astype(str)

    fin = open("model.pkl","rb")
    model = pickle.load(fin)
    fin.close()
    price = model.predict(df_onehot)
    return jsonify({"Price":int(price[0])})

if __name__ == "__main__":
    app.run(port=80,debug=False)
