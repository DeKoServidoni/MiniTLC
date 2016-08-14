Rails.application.routes.draw do

	namespace :api, defaults: { format: :json }, path: '/'  do
		namespace :v1 do
			resources :events, :only => [:index, :create, :destroy]
    	end
  end 
  
end
