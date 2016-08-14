class CreateEvents < ActiveRecord::Migration[5.0]
  def change
    create_table :events do |t|
      t.string :title
      t.string :desc
      t.string :local
      t.string :when
      t.string :hour

      t.timestamps
    end
  end
end
