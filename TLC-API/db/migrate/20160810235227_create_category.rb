class CreateCategory < ActiveRecord::Migration[5.0]
  def change
    create_table :categories do |t|
      t.string :name
    	t.integer :min_age
    	t.integer :max_age
    	t.integer :status

      t.references :club, foreign_key: true

      t.timestamps
    end
  end
end
