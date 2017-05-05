package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;

import java.time.LocalDateTime;
import java.util.UUID;

public class Chunk {
	public static final int SIZE = 256;
	public static final int TOTAL_SIZE = 256 * 256;

	private ChunkData data;
	private Vector2 position;
	private LocalDateTime lastAccessedTime;
	private UUID uniqueChunkId;


	public Chunk(ChunkData data, Vector2 position) {
		this.updateLastAccessedTime();
		this.data = data;
		this.position = position;
		this.uniqueChunkId = UUID.randomUUID();
	}

	public void update() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				Block block = (Block) Item.fromID(this.data.blocks[x + y * SIZE]);

				if (block != null) {
					block.updateBlock(x, y);
				}
			}
		}

		this.updateLastAccessedTime();
	}

	public void updateGraphics() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				Block block = (Block) Item.fromID(this.data.blocks[x + y * SIZE]);

				if (block != null) {
					block.updateBlockStyle(x, y);
				}
			}
		}

		this.updateLastAccessedTime();
	}

	private void updateLastAccessedTime() {
		this.lastAccessedTime = LocalDateTime.now();
	}

	public short getBlock(int globalX, int globalY) {

		return this.getBlockInside(globalX - this.getX(), globalY - this.getY());
	}

	public short getBlockInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
		}

		return this.data.blocks[x + y * SIZE];
	}

	public void setBlock(short id, int globalX, int globalY) {
		this.setBlockInside(id, globalX - this.getX(), globalY - this.getY());
	}

	public void setBlockInside(short id, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}

		this.data.blocks[x + y * SIZE] = id;
	}

	public byte getBlockHP(int globalX, int globalY) {

		return this.getBlockHPInside(globalX - this.getX(), globalY - this.getY());
	}

	public byte getBlockHPInside(int x, int y) {
		if (!this.isInside(x, y)) {

			return ItemID.none;
		}

		return this.data.blocksHealth[x + y * SIZE];
	}

	public void setBlockHP(byte hp, int globalX, int globalY) {
		this.setBlockHPInside(hp, globalX - this.getX(), globalY - this.getY());

	}

	public void setBlockHPInside(byte hp, int x, int y) {
		if (!this.isInside(x, y)) {

			return;
		}

		this.data.blocksHealth[x + y * SIZE] = hp;
	}

	public short getWall(int globalX, int globalY) {

		return this.getWallInside(globalX - this.getX(), globalY - this.getY());
	}

	public short getWallInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
		}

		return this.data.walls[x + y * SIZE];
	}

	public void setWall(short id, int globalX, int globalY) {
		this.setWallInside(id, globalX - this.getX(), globalY - this.getY());
	}

	public void setWallInside(short id, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}

		this.data.walls[x + y * SIZE] = id;
	}

	public byte getWallHP(int globalX, int globalY) {
		return this.getWallHPInside(globalX - this.getX(), globalY - this.getY());
	}

	public byte getWallHPInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
		}

		return this.data.wallsHealth[x + y * SIZE];
	}

	public void setWallHP(byte hp, int globalX, int globalY) {

		this.setWallHPInside(hp, globalX - this.getX(), globalY - this.getY());
	}

	public void setWallHPInside(byte hp, int x, int y) {
		if (!this.isInside(x, y)) {

			return;
		}

		this.data.wallsHealth[x + y * SIZE] = hp;
	}

	public ChunkData getData() {

		this.updateLastAccessedTime();

		return this.data;
	}

	public int getGridX() {

		this.updateLastAccessedTime();

		return (int) this.position.x;
	}

	public int getGridY() {

		this.updateLastAccessedTime();

		return (int) this.position.y;
	}

	public int getX() {

		this.updateLastAccessedTime();

		return (int) this.position.x * SIZE;
	}

	public int getY() {

		this.updateLastAccessedTime();

		return (int) this.position.y * SIZE;
	}

	private boolean isInside(int x, int y) {

		this.updateLastAccessedTime();

		return (x >= 0 && x < SIZE && y >= 0 && y < SIZE);
	}

	public LocalDateTime getLastAccessedTime() { return this.lastAccessedTime; }
}