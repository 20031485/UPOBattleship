package utils;

import java.io.Serializable;

/**
 * Utility Enum containing the state a Player could be in after 
 * receiving a hit from the Computer
 * @author 20027017 & 20031485
 *
 */
public enum PlayerState implements Serializable{
	WATER,
	HIT,
	HITANDSUNK;
}
