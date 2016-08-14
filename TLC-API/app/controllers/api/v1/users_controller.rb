class Api::V1::UsersController < ApplicationController

	# GET	/users	index	users_path	page to list all users
	def index
		@user = User.find(request.headers[:id])
		render json: {
						name: @user.name, 
						email: @user.email, 
						password: @user.password,
						created_at: @user.created_at,
						updated_at: @user.updated_at
					},
					status: :ok
	end
  
  	# POST	/users	create	users_path	create a new user
	def create
		@user = User.new(
							name: request.headers[:name], 
							email: request.headers[:email],
							password: request.headers[:password]
						)

		if @user.valid? 
			result = true
			status = :created
			code = "user.created"
			error = ""
			@user.save
		else
			result = false
			code = "user.not.created"
			error = "User information didn't match the requirements"
			status = :ok
		end

		render json: { 
						result: result,
						code: code,
						error: error
					},
					status: status
	end

	# DELETE	/users/1	destroy	user_path(user)	delete user
	def destroy
		@user = User.find(params[:id])
		result = @user.destroy

		render json: {
						message: "User removed!",
						result: result
					},
				 	status: :ok
	end
end