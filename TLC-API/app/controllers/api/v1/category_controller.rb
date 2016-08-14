class Api::V1::CategoryController < ApplicationController

	def index
		@category = Category.find(request.headers[:id])
		render json: {status: true, result: @category}, status: 200
	end

	def create
		@club = Club.find(1)

		@category = @club.categories.build(
							name: request.headers[:name], 
							min_age: request.headers[:min],
							max_age: request.headers[:max],
							status: 1
						)

		if @category.valid? 
			result = true
			status = :created
			code = "category.created"
			error = ""
			@category.save
		else
			result = false
			code = "category.not.created"
			error = "Category information didn't match the requirements"
			status = :ok
		end

		render json: { 
						result: result,
						code: code,
						error: error
					},
					status: status
	end

	def destroy
		@category = Category.find(params[:id])
		result = @category.destroy

		render json: {
						message: "Category removed!",
						result: result
					},
				 	status: :ok
	end
end