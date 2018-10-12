# Portal Jumper

## Language and platform
Project created and executed in Eclipse using the Java language.

## How it works?
There are two projects "PortalJumper Server" and "PortalJumper Client". To run the game you must run a server project and click on the third option "Turn server on" which will leave the port listening to the waiting of a client. After the server is connected, in the client project click on "Connect to server", after everything is connected both can click on Start Game.
The project is configured to run the two instantiations on the same machine. To change this, the client must have the IP number of the server this can be changed in
PortalJumper Client> src> com.redes.game.socket> network> Line 15 in the serverIP variable.

## Movement
To move the player 1 use the WASD keys.
To move the player 2 use the IJKL keys.

## Purpose of the game
Capture 3 portal that are arranged somewhere on the map.
There are 4 maps, mapped by reading pixels.

_There are some bugs._
