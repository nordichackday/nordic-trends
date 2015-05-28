from flask import Flask, jsonify, request
from datetime import datetime
import urllib2
import json
import re
import random
import uuid

app = Flask(__name__)

@app.route('/')
def index():
    return 'Index'

@app.route('/tags')
def tags():
    tags = []
    for i in range(10):
        tag = {
            "name" : str(uuid.uuid4())[0:5],
            "num"  : random.randint(1, 100)
        }
        tags.append(tag)
    data = { "tags": tags }

    return jsonify(data)

if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)
