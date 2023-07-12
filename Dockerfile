FROM ubuntu

RUN apt-get update
RUN apt-get -y upgrade
RUN apt-get install -y python3
RUN apt-get install -y python3-pip

RUN mkdir app

COPY requirements.txt /app/
COPY ServerProgram/app.py /app/
COPY ServerProgram/onehot_encoder.pkl /app/
COPY ServerProgram/model.pkl /app/

RUN pip install -r /app/requirements.txt

EXPOSE 80

CMD ["python3","/app/app.py"]
