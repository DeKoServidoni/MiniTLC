require 'test_helper'

class UserTest < ActiveSupport::TestCase

	def setup 
		@user = User.new(name: "User test 1", email: "user1@test.com", password: "usertest1")
	end

	test "should be valid" do
		assert @user.valid?
	end

	# Presence validations

	test "name should be present" do
		@user.name = ""
		assert_not @user.valid?
	end

	test "email should be present" do
    	@user.email = ""
    	assert_not @user.valid?
  	end

  	test "password should be present" do
  		@user.password = ""
  		assert_not @user.valid?
  	end

  	# Length validations

  	test "name should not be too long" do
    	@user.name = "a" * 51
    	assert_not @user.valid?
  	end

  	test "email should not be too long" do
    	@user.email = "a" * 300 + "@test.com"
    	assert_not @user.valid?
  	end

  	test "password should not be too long" do
  		@user.password = "a" * 16
  		assert_not @user.valid?
  	end
end
