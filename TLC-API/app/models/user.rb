class User < ApplicationRecord
	validates :name,  presence: true, length: { maximum: 50 }
	validates :email, presence: true, length: { maximum: 255 }
	validates :password, presence: true, length: { maximum: 15 }
	validates :token, presence: false

	before_create :generate_authentication_token

	private

	def generate_authentication_token
      loop do
        self.token = SecureRandom.base64(64)
        break unless User.find_by(token: token)
      end
    end
end
