from flask import Flask, request, jsonify, make_response  # 서버 구현을 위한 Flask 객체 import
from flask_restx import Api, Resource, Namespace, fields  # Api 구현을 위한 Api 객체 import
from flask_cors import CORS
import data
import test1

app = Flask(__name__)  # Flask 객체 선언, 파라미터로 어플리케이션 패키지의 이름을 넣어줌.
# cors = CORS(app, resources={
#     r"*" : {"origin" : "*"}
# })
# app.config['JSON_AS_ASCII'] = False  # 인코딩 문제로 추가해봄.
api = Api(
    app,
    version='1.0',
    title="recommend.xyz API",
    description="영화 추천 API",
    terms_url="/",
    contact="",
    license="MIT",
    # authorizations=authorizations,
    # security="Authorization",
)  # Flask 객체에 Api 객체 등록

model = api.model('model', {
    'movie_name': fields.String(description='파라미터', required=True, example="Star Wars@The Hobbit: The Battle of the Five Armies@Iron Man")
})

@api.route('/postrecommendapikey')
class postAllData(Resource):
    @api.expect(model)
    # @api.response(200, 'Succ  ess', todo_fields_with_id)
    @api.response(200, 'Success')
    @api.response(500, 'Failed')
    def post(self):
        param = request.get_json()['movie_name']
        recommend_result = param.split('@') # list로 반환

        print('************** req header: \n', request.headers)
        print('************** req data: ', request.data)
        

        return test1.run(recommend_result)


# @api.route('/getrecommendapikey')
# class getAllData(Resource):
#     @api.expect(model)
#     #@api.response(200, 'Success', todo_fields_with_id)
#     @api.response(200, 'Success')
#     @api.response(500, 'Failed')
#     def get(self):
#             param = request.args.get('movie_name')
#             # recommend_result = param.split('@') # list로 반환

#             # return test1.run(recommend_result)
#             return param


        

        #return jsonify(data.data_result)
        #return test.hamsu(int(num))

if __name__ == "__main__":
    # app.run(debug=True)
    app.run(debug=True, host='0.0.0.0', port=5000)