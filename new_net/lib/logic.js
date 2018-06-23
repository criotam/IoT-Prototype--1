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

 /**
 * ReadSensor
 * @param {org.criotam.prototype.sensor.readexperiment1lcData} readexperiment1lcData
 * @transaction
 */
async function readexperiment1lcData(experiment1lcData) {
    var NS = 'org.criotam.prototype.sensor';
    let Raw_value = experiment1lcData.Raw_value;
  	let lc1_Raw_value = experiment1lcData.lc1_Raw_value;
  	let lc2_Raw_value = experiment1lcData.lc2_Raw_value;
    let expId = getExperimentId(Raw_value);
    let factory = getFactory();
  	lc1_Raw_value = getExplc(Raw_value);
  lc2_Raw_value = getExplc(Raw_value);
    experiment1lcData.experiment1.lc1_Raw_value = lc1_Raw_value[0];
    experiment1lcData.experiment1.lc2_Raw_value = lc2_Raw_value[1];
    experiment1lcData.experiment1.experimentId = expId;
    experiment1lcData.experiment1.Raw_value = Raw_value;
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.Experiment1lc');
    await assetRegistry.update(experiment1lcData.experiment1);
   /* await assetRegistry.update(experiment1lcData.experiment1.lc2_Raw_value);
    await assetRegistry.update(experiment1lcData.experiment1.Raw_value);*/

    let event = factory.newEvent(NS,'experiment1lcDataread');
    event.experimentId = experiment1lcData.experiment1.experimentId;
    emit(event);
 }


 /**
 * addExperiment
 * @param {org.criotam.prototype.sensor.experiment1lcDataAdd} experiment1lcDataAdd
 * @transaction
 */

async function experiment1lcDataAdd(experiment1lcData) {
    var NS = 'org.criotam.prototype.sensor';
    let factory = getFactory();
    let Experiment = factory.newResource(NS, "Experiment1lc", experiment1lcData.experimentId);
    Experiment.experimentId = experiment1lcData.experimentId;
    Experiment.Raw_value = experiment1lcData.Raw_value
    Experiment.lc1_Raw_value = experiment1lcData.lc1_Raw_value ;
    Experiment.lc2_Raw_value = experiment1lcData.lc2_Raw_value ;
    Experiment.experimenter = experiment1lcData.experimenter  ;
    Experiment.player = experiment1lcData.player;
     
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.sensor.Experiment1lc');
    await assetRegistry.add(Experiment);

    let event = factory.newEvent(NS,'experiment1lcDataAdded');
    event.experimentId = experiment1lcData.experimentId;
    emit(event);
 }

// functions to retrieve relevant data from string recieved

// sample data identifier_exp1lc:-0.01:0.06:498706

 function getExperimentId(data) {
        let sensorData = data.split("$");
        console.log(sensorData);
        let singleDatapoint = sensorData[0].split(":");
        let experiment_number = singleDatapoint[0].split("_")[1].substr(3,1);
        let experimentId = "E"+ "0" + experiment_number;
        return experimentId;
 }

 function getExperimentNumber(data) {
    var sensorData = data.split("$");
    var singleDatapoint = sensorData[0].split(":");
    var experiment_number = singleDatapoint[0].split("_")[1].substr(3,1);
    return experiment_number;
 }

 function getExplc(data) {
    let lc1_Raw_value = [];
    let lc2_Raw_value = [];
   var sensorData = data.split("$");
   var l = sensorData.length;
   
   
   for(var i = 0; i < l; i++ ) {
       console.log(i);
       var singleDatapoint = sensorData[i].split(":"); //identifier_exp1lc:-0.01:0.06:498706
       lc1_Raw_value.push(singleDatapoint[1]);
       lc2_Raw_value.push(singleDatapoint[2]);
       console.log(lc1_Raw_value);
       console.log(lc2_Raw_value);
   }
   return [lc1_Raw_value, lc2_Raw_value];
 }

 function getExpemg(data) {
     let emg_Raw_value = [];
    var sensorData = data.split("$");
    var l = sensorData.length;
    for(var i =0; i <l ; i++){
        var singleDatapoint = sensorData[i].split(":");
        emg_Raw_value.push(singleDatapoint[1]);
    }
    return emg_Raw_value;
 }

 function getExp3fp(data) {
     let lc1_Raw_value = [];
     let lc2_Raw_value = [];
     let lc3_Raw_value = [];
    var sensorData = data.split("$");
    var l = sensorData.length;
    console.log(sensorData);
    for(var i =0; i < l; i++ ) {
        var singleDatapoint = sensorData[i].split(":");
        lc1_Raw_value.push(singleDatapoint[1]);
        lc2_Raw_value.push(singleDatapoint[2]);
        lc3_Raw_value.push(singleDatapoint[3]);

    }
    return {lc1_Raw_value, lc2_Raw_value, lc3_Raw_value}
 }
