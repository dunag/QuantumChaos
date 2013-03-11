package com.theboxbrigade.quantumchaos;

import java.util.Iterator;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.*;

/**
 * 3D tiles array:
 * [nLayers][nColumns][nRows]
 * @param nLayers: Index of layers. Index 0 is the background layer,
 *     which should always be used to test walkable. Index 1 is
 *     the additional object layer, which can potentially be
 *     used to test obstructed.
 * @param nColumns: Index of columns. [0,0] is the lower left, so [0,1]
 *     is in the same column and NORTH of [0,0].
 * @param nRows: Index of rows. [0,0] is the lower left, so [1,0] is in
 *     the same row and EAST of [0,0].
 */
public class TileManager {
	private TiledMap tileMap;
	private Tile[][][] tiles;
	private int nColumns, nRows;
	private int nLayers = 0;
	private TiledMapTileLayer[] layers;
	
	public TileManager(TiledMap tileMap) {
		this.tileMap = tileMap;
		Iterator<MapLayer> it = tileMap.getLayers().iterator();
		while (it.hasNext()) {
			it.next();
			nLayers++;
		}
		loadLayers();
		tiles = new Tile[nLayers][nColumns][nRows];
		loadTiles();
	}
	
	public int getNumberOfLayers() {
		return nLayers;
	}
	
	public TiledMapTileLayer getLayer(int index) {
		if (index >= 0 && index < nLayers)
			return (TiledMapTileLayer)tileMap.getLayers().getLayer(index);
		return null;
	}
	
	private void loadLayers() {
		layers = new TiledMapTileLayer[nLayers];
		int cols, rows;
		for (int i=0; i<nLayers; i++) {
			layers[i] = getLayer(i);
			cols = layers[i].getWidth();
			rows = layers[i].getHeight();
			if (cols > nColumns) nColumns = cols;
			if (rows > nRows) nRows = rows;
		}
	}
	
	private void loadTiles() {
		for (int layer=0; layer<nLayers; layer++) {
			for (int col=0; col<nColumns; col++) {
				for (int row=0; row<nRows; row++) {
					tiles[layer][col][row] = new Tile(layers[layer].getCell(col, row).getTile());
					if (tiles[layer][col][row].getProperties().get("walkable").equals("true")) {
						tiles[layer][col][row].setWalkable(true);
					}
				}
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x > 0 && x < nColumns && y > 0 && y < nRows) {
			return tiles[0][x][y];
		}
		return null;
	}
	
	public Tile[][][] getAllTiles() {
		return tiles;
	}
}
