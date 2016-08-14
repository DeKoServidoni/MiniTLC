class Api::V1::ClubsController < ApplicationController

	def index
		@club = Club.find(request.headers[:id])
		render json: {status: true, result: @club}, status: 200
	end

	def create
		@club = Club.new(
							name: request.headers[:name], 
							shield: request.headers[:shield],
							founding_date: request.headers[:founding],
							stadium_name: request.headers[:stadium],
							locale: request.headers[:locale],
							capacity: request.headers[:capacity]
						)

		if @club.valid? 
			result = true
			status = :created
			code = "club.created"
			error = ""
			@club.save
		else
			result = false
			code = "club.not.created"
			error = "Club information didn't match the requirements"
			status = :ok
		end

		render json: { 
						result: result,
						code: code,
						error: error
					},
					status: status
	end
end