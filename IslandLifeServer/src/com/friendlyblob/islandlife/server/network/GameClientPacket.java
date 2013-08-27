package com.friendlyblob.islandlife.server.network;

import java.nio.BufferUnderflowException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mmocore.network.ReceivablePacket;

import com.friendlyblob.islandlife.server.network.packets.server.ActionFailed;
import com.friendlyblob.islandlife.server.network.packets.server.ServerPacket;

public abstract class GameClientPacket extends ReceivablePacket<GameClient>{

	protected static final Logger log = Logger.getLogger(GameClientPacket.class.getName());
	
	@Override
	protected boolean read() {
		try {
			readImpl();
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Client: " + getClient().toString() + " - Failed reading: " + getType() + e.getMessage(), e);
			
			if (e instanceof BufferUnderflowException)
			{
				getClient().onBufferUnderflow();
			}
		}
		return false;
	}
	
	protected abstract void readImpl();

	@Override
	public void run() {
		try {
			runImpl();
			
		} catch (Throwable t) {
			log.log(Level.SEVERE, "Client: " + getClient().toString() + " - Failed running: " + getType() + " - L2J Server Version: "+ t.getMessage(), t);
		}
	}
	
	protected abstract void runImpl();
	
	/**
	 * Sends a game server packet to the client.
	 * @param gsp the game server packet
	 */
	protected final void sendPacket(ServerPacket sp)
	{
		getClient().sendPacket(sp);
	}
	
	/**
	 * @return A String with this packet name for debugging purposes
	 */
	public abstract String getType();
	
	/**
	 * Overridden with true value on some packets that should disable spawn protection (RequestItemList and UseItem only)
	 * @return
	 */
	protected boolean triggersOnActionRequest()
	{
		return true;
	}
	

	// TODO get active char
	
	protected final void sendActionFailed(){
		if (getClient() != null) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
		}
	}

}
