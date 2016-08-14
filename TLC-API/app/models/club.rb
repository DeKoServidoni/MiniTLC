class Club < ApplicationRecord
	has_many :categories, dependent: :destroy

	validates :name, presence: true, length: { maximum: 255 }
	validates :shield, presence: true, length: { maximum: 255 }
	validates :founding_date, presence: true, length: { maximum: 255 }
	validates :stadium_name, presence: true, length: { maximum: 255 }
	validates :locale, presence: true, length: { maximum: 255 }
	validates :capacity, presence: true, length: { maximum: 255 }
end
