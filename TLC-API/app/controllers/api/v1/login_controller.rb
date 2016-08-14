class Api::V1::LoginController < ApplicationController

	# Verify the user and return the token of it
	def create
		@username = request.headers[:username]
		@password = request.headers[:password]

		user = User.find_by(email: @username)
		if user && user.password == @password
			render json: {status: true, result: user}, status: 200
		else
			render json: {
						status: false,
						result: nil,
						code: "invalid.login",
						error: "Ops! There is a problema with your credentials!"
					},
					status: 404
		end
	end
end