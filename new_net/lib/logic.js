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
    // Save the old value of the asset.
    const oldValue = tx.sensor.value;
    console.log(tx.sensor.value);
    // Update the asset with the new value.
    tx.sensor.value = tx.newValue;

    // Get the asset registry for the asset.
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.Sensor');
    // Update the asset in the asset registry.
    await assetRegistry.update(tx.sensor.value);

    // Emit an event for the modified asset.
    let event = getFactory().newEvent('org.criotam.prototype.sensor', 'sensorRead');
    event.sensor = tx.sensor;
    event.oldValue = oldValue;
    event.newValue = tx.newValue;
    emit(event);
}


async function addPlayer(tx) {
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor');
    let event = getFactory().newEvent('org.criotam.prototype.sensor', 'addedPlayer');
    event.player = tx.player;
    event.player_id = tx.player_id;
    event.player_name = tx.player_name;
    event.player_age = tx.player_age;
    event.player_weight = tx.player_weight;
    event.player_height = tx.player_height;
    event.plaeyr_sex = tx.player_sex;
    emit(event);

}
