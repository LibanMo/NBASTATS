FROM python:3.9-slim

COPY requirements.txt ./requirements.txt
RUN pip install -r requirements.txt
RUN pip install gunicorn

# Copy files to your container
COPY . ./

CMD gunicorn -b 0.0.0.0:8050 main:server
