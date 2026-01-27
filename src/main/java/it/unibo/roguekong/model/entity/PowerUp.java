package it.unibo.roguekong.model.entity;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;

public interface PowerUp {
    public void applyEffect(PlayerImpl player);
    public void removeEffect(PlayerImpl player);

    String getName();
    String getDescription();
}
