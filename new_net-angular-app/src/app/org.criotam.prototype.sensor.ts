import {Asset} from './org.hyperledger.composer.system';
import {Participant} from './org.hyperledger.composer.system';
import {Transaction} from './org.hyperledger.composer.system';
import {Event} from './org.hyperledger.composer.system';
// export namespace org.criotam.prototype.sensor{
   export class Scientist extends Participant {
      scientistId: string;
      userName: string;
      Email: string;
      Password: string;
   }
   export class Player extends Asset {
      player_id: string;
      player_name: string;
      player_age: string;
      player_weight: string;
      player_height: string;
      player_sex: string;
      experimenter: Scientist;
   }
   export class Sensor extends Asset {
      sensorId: string;
      sensorMAC: string;
      value: string[];
      owner: Scientist;
      player: Player;
   }
   export class readSensor extends Transaction {
      sensor: Sensor;
      newValue: string[];
   }
   export class sensorRead extends Event {
      sensor: Sensor;
      oldValue: string[];
      newValue: string[];
   }
   export class addPlayer extends Transaction {
      player_id: string;
      player_name: string;
      player_age: string;
      player_weight: string;
      player_height: string;
      player_sex: string;
   }
   export class addedPlayer extends Event {
      player_id: string;
   }
   export class addSensor extends Transaction {
      sensorId: string;
      sensorMAC: string;
      value: string[];
      owner: Scientist;
      player: Player;
   }
   export class addedSensor extends Event {
      sensorId: string;
   }
   export class addScientist extends Transaction {
      scientistId: string;
      userName: string;
      Email: string;
      Password: string;
   }
   export class addedScientist extends Event {
      scientistId: string;
   }
// }
