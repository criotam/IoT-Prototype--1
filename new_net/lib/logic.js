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
 
async function readSensor(sensorData) {
    var NS = 'org.criotam.prototype.sensor'
    // Save the old value of the asset.
    const Raw_oldValue = sensorData.sensor.Raw_oldValue;
    const Fx_oldValue = sensorData.sensor.Fx_oldValue;
    const Fy_oldValue = sensorData.sensor.Fy_oldValue;
    const Fz_oldValue = sensorData.sensor.Fz_oldValue;
    const Mx_oldValue = sensorData.sensor.Mx_oldValue;
    const My_oldValue = sensorData.sensor.My_oldValue;
    const Mz_oldValue = sensorData.sensor.Mz_oldValue;
    // Update the asset with the new value.
    sensorData.sensor.Raw_value = sensorData.Raw_newValue;
    sensorData.sensor.Fx_value = sensorData.Fx_newValue;
    sensorData.sensor.Fy_value = sensorData.Fy_newValue;
    sensorData.sensor.Fz_value = sensorData.Fz_newValue;
    sensorData.sensor.Mx_value = sensorData.Mx_newValue;
    sensorData.sensor.My_value = sensorData.My_newValue;
    sensorData.sensor.Mz_value = sensorData.Mz_newValue;

    // Get the asset registry for the asset.
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.Sensor');
    // Update the asset in the asset registry.
    await assetRegistry.update(sensorData.sensor);

    // Emit an event for the modified asset.
    let event = getFactory().newEvent(NS, 'sensorRead');
    event.sensor = sensorData.sensor;
    event.Raw_oldValue = Raw_oldValue;
    event.Raw_newValue = sensorData.Raw_newValue;
    event.Fx_oldValue = sensorData.Fx_oldValue;
    event.Fy_oldValue = sensorData.Fy_oldValue;
    event.Fz_oldValue = sensorData.Fz_oldValue;
    event.Mx_oldValue = sensorData.Mx_oldValue;
    event.My_oldValue = sensorData.My_oldValue;
    event.Mz_oldValue = sensorData.My_oldValue;
    emit(event);
}

/**
 * addPlayer
 * @param {org.criotam.prototype.sensor.addPlayer} addPlayer
 * @transaction
 */

async function addPlayer(playerData) {
    var NS = 'org.criotam.prototype.sensor';

    let factory = getFactory();
    let Player = factory.newResource(NS, "Player", playerData.player_id);
    Player.player_id = playerData.player_id;
    Player.player_name = playerData.player_name;
    Player.player_age = playerData.player_age;
    Player.player_weight = playerData.player_weight;
    Player.player_height = playerData.player_height;
    Player.player_sex = playerData.player_sex;

    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.Player');
    await assetRegistry.add(Player);

    let event = factory.newEvent(NS, 'addedPlayer');
    event.player_id = playerData.player_id;
    emit(event);
}

/**
 * addSensor
 * @param {org.criotam.prototype.sensor.addSensor} addSensor
 * @transaction
 */


 async function addSensor(sensorData) {
    var NS = 'org.criotam.prototype.sensor';
    let factory = getFactory();
    let Sensor = factory.newResource(NS, "Sensor", sensorData.sensorId);
    Sensor.sensorId = sensorData.sensorId;
    Sensor.sensorMAC = sensorData.sensorMAC;
    Sensor.owner = sensorData.owner;
    Sensor.player = sensorData.player;

    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.Sensor');
    await assetRegistry.add(Sensor);

    let event = factory.newEvent(NS,'addedSensor');
    event.sensorId = sensorData.sensorId;
    emit(event);
 }

 /**
 * addScientist
 * @param {org.criotam.prototype.sensor.addScientist} addScientist
 * @transaction
 */

 async function addScientist(scientistData) {
    var NS = 'org.criotam.prototype.sensor';
    let factory = getFactory();
    let Scientist = factory.newResource(NS,"Scientist",scientistData.scientistId);
    Scientist.scientistId = scientistData.scientistId;
    Scientist.userName = scientistData.userName;
    Scientist.Email = scientistData.Email;
    Scientist.Password = scientistData.Password;
    const participantRegistry = await getParticipantRegistry('org.criotam.prototype.sensor.Scientist');
    await participantRegistry.add(Scientist);

    let event = factory.newEvent(NS, 'addedScientist');
    event.scientistId = scientistData.scientistId;
    emit(event);

 }


