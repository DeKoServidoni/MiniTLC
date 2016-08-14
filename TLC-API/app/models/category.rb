class Category < ApplicationRecord
	belongs_to :club

	validates :name,  presence: true, length: { maximum: 255 }
	validates :min_age, presence: true
	validates :max_age, presence: true
	validates :status, presence: true
end
