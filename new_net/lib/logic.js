/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

'use strict';
/**
 * Write your transction processor functions here
 */

/**
 * readSensor
 * @param {org.criotam.prototype.sensor.readSensor} readSensor
 * @transaction
 */

 
async function readSensor(tx) {
    var NS = 'org.criotam.prototype.sensor'
    // Save the old value of the asset.
    const oldValue = tx.sensor.value;
    // Update the asset with the new value.
    tx.sensor.value = tx.newValue;

    // Get the asset registry for the asset.
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.Sensor');
    // Update the asset in the asset registry.
    await assetRegistry.update(tx.sensor);

    // Emit an event for the modified asset.
    let event = getFactory().newEvent(NS, 'sensorRead');
    event.sensor = tx.sensor;
    event.oldValue = oldValue;
    event.newValue = tx.newValue;
    emit(event);
}

/**
 * readSensor
 * @param {org.criotam.prototype.sensor.addPlayer} addPlayer
 * @transaction
 */

async function addPlayer(playerData) {
    var NS = 'org.criotam.prototype.sensor'
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.addPlayer');
    var factory = getFactory();
    var Player = factory.newResources(NS, "Player", playerData.player);
    Player.player_id = playerData.playerId;
    Player.player_name = playerData.player_name;
    Player.player_age = playerData.player_age;
    Player.player_weight = playerData.player_weight;
    Player.player_height = playerData.player_height;
    Player.player_sex = playerData.player_sex;

    await assetRegistry.add(Player);

    let event = factory.newEvent(NS, 'addedPlayer');
    event.player = playerData.player
    emit(event);
}