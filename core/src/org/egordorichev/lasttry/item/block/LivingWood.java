package org.egordorichev.lasttry.item.block;

public class LivingWood extends Block {
	public LivingWood(String id) {
		super(id);
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}
}