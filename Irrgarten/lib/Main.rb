require_relative 'Game'
require_relative 'UI/TextUI'
require_relative 'Control/Controller'

g = Game.new(2)
view = UI::TextUI.new
ctr = Control::Controller.new(g, view)
ctr.play

