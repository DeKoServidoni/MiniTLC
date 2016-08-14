Rails.application.routes.draw do

	namespace :api, defaults: { format: :json }, path: '/'  do
		namespace :v1 do
			resources :users, :only => [:index, :create, :destroy]
			resources :login, :only => [:create]
			resources :clubs, :only => [:index, :create]
			resources :category, :only =>[:index, :create, :destroy]
    	end
  end 
  
end
