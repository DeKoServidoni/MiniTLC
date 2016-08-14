class ApplicationController < ActionController::API
	# Prevent CSRF attacks by raising an exception.
  	# For APIs, you may want to use :null_session instead.
  	#protect_from_forgery with: :null_session
  	
  	before_action :destroy_session
	rescue_from ActiveRecord::RecordNotFound, with: :not_found

  	def destroy_session
    	request.session_options[:skip] = true
  	end

  	def not_found
  		return api_error(status: 404, errors: 'Invalid service! [Not found]')
  	end

  	def authenticate_user!
    	token, options = ActionController::HttpAuthentication::Token.token_and_options(request)

    	user_email = options.blank?? nil : options[:email]
    	user = user_email && User.find_by(email: user_email)

    	if user && ActiveSupport::SecurityUtils.secure_compare(user.authentication_token, token)
      		@current_user = user
    	else
      		return unauthenticated!
    	end
  	end
end