class Event < ApplicationRecord
	validates :title,  presence: true, length: { maximum: 50 }
	validates :desc, presence: true, length: { maximum: 255 }
	validates :local, presence: true, length: { maximum: 50 }
	validates :when, presence: true, length: { maximum: 50 }
	validates :hour, presence: true, length: { maximum: 50 }
end
