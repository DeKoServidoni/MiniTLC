class Api::V1::EventsController < ApplicationController

	# GET
	def index
		@event = Event.find(request.headers[:id])
		render json: { event: @event }, status: :ok
	end
  
  	# POST
	def create
		@event = Event.new(
							title: request.headers[:eventname], 
							desc: request.headers[:eventdesc],
							local: request.headers[:eventlocal],
							when: request.headers[:eventwhen],
							hour: request.headers[:eventhour],
						)

		if @event.valid? 
			message = "Event created!"
			status = :created
			@event.save
		else
			message = "Failed to create event."
			status = :ok
			@event = nil
		end

		render json: { message: message, event: @event }, status: status
	end

	# DELETE
	def destroy
		@event = Event.find(params[:id])
		result = @event.destroy

		render json: { message: "Event removed!", result: result }, status: :ok
	end
end