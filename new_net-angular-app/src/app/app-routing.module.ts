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

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';

import { PlayerComponent } from './Player/Player.component';
import { SensorComponent } from './Sensor/Sensor.component';

import { ScientistComponent } from './Scientist/Scientist.component';

import { readSensorComponent } from './readSensor/readSensor.component';
import { addPlayerComponent } from './addPlayer/addPlayer.component';
import { addSensorComponent } from './addSensor/addSensor.component';
import { addScientistComponent } from './addScientist/addScientist.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'Player', component: PlayerComponent },
  { path: 'Sensor', component: SensorComponent },
  { path: 'Scientist', component: ScientistComponent },
  { path: 'readSensor', component: readSensorComponent },
  { path: 'addPlayer', component: addPlayerComponent },
  { path: 'addSensor', component: addSensorComponent },
  { path: 'addScientist', component: addScientistComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
 imports: [RouterModule.forRoot(routes)],
 exports: [RouterModule],
 providers: []
})
export class AppRoutingModule { }
