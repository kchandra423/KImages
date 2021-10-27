[![CircleCI](https://circleci.com/gh/kchandra423/KImages/tree/main.svg?style=svg)](https://circleci.com/gh/kchandra423/KImages/tree/main)
![Test results](https://camo.githubusercontent.com/94b4db49ee9822a23c1268e81433c6915636fd8403095eada3e10552133e7b24/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f74657374732d312532307061737365642d73756363657373)
# Textures

## Overview
A simple library to represent images and specifically gifs as movable and resizable textures. Uses processing to draw images. Supports jpeg, png, and gif files. Although processing already supports gifs, they are not properly animated, and would just be shown as a png, like this:
![static logo](https://i.ytimg.com/vi/nrdYDI6lTlA/maxresdefault.jpg)

However, this library is able to "animate" the gif properly so that is correctly shown like this: 
![nonstatic logo](https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/67401945-34fc-46b8-8e8f-1982847277d4/ddba22b-2fad9d00-1d3f-4ec8-a65d-199a09dfa4e1.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzY3NDAxOTQ1LTM0ZmMtNDZiOC04ZThmLTE5ODI4NDcyNzdkNFwvZGRiYTIyYi0yZmFkOWQwMC0xZDNmLTRlYzgtYTY1ZC0xOTlhMDlkZmE0ZTEuZ2lmIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.p1RcYkkOBXh0fzpoZxaTbE1_xNWLfoLqEZv1_0utuzU)

## Usage

### Adding to project
The library is in a jar file called textures in the dist folder. You will also need to add all the supports jars in the lib folder.

### In projects
Textures should be created using the TextureBuilder class, as the Texture class itself is abstract since gifs use a different subclass than other image types.

## Credit
The gifs were animated with the help of this gif decoding library: https://github.com/DhyanB/Open-Imaging

Uses the Processing graphics library to display images.

With help from :https://github.com/Nathaniel-github

## Possible future plans
In the future I might update the library to be able to do fancier stuff with the gifs, such as having them only play once, or altering the delay of certain or all frames of the gif, at https://github.com/Nathaniel-github 's request.
