class CreateClub < ActiveRecord::Migration[5.0]
  def change
    create_table :clubs do |t|
    	t.string :name
      	t.string :shield
      	t.string :founding_date
      	t.string :stadium_name
      	t.string :locale
      	t.string :capacity

      	t.timestamps
    end
  end
end
