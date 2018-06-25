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
import { Experiment1lcComponent } from './Experiment1lc/Experiment1lc.component';
import { Experiment2lcComponent } from './Experiment2lc/Experiment2lc.component';
import { Experiment2emgComponent } from './Experiment2emg/Experiment2emg.component';
import { Experiment3fpComponent } from './Experiment3fp/Experiment3fp.component';
import { Experiment3emgComponent } from './Experiment3emg/Experiment3emg.component';

import { ScientistComponent } from './Scientist/Scientist.component';

import { experiment1lcDataAddComponent } from './experiment1lcDataAdd/experiment1lcDataAdd.component';
import { experiment2lcDataAddComponent } from './experiment2lcDataAdd/experiment2lcDataAdd.component';
import { experiment2emgDataAddComponent } from './experiment2emgDataAdd/experiment2emgDataAdd.component';
import { experiment3fpDataAddComponent } from './experiment3fpDataAdd/experiment3fpDataAdd.component';
import { experiment3emgDataAddComponent } from './experiment3emgDataAdd/experiment3emgDataAdd.component';
import { addPlayerComponent } from './addPlayer/addPlayer.component';
import { addScientistComponent } from './addScientist/addScientist.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'Player', component: PlayerComponent },
  { path: 'Experiment1lc', component: Experiment1lcComponent },
  { path: 'Experiment2lc', component: Experiment2lcComponent },
  { path: 'Experiment2emg', component: Experiment2emgComponent },
  { path: 'Experiment3fp', component: Experiment3fpComponent },
  { path: 'Experiment3emg', component: Experiment3emgComponent },
  { path: 'Scientist', component: ScientistComponent },
  { path: 'experiment1lcDataAdd', component: experiment1lcDataAddComponent },
  { path: 'experiment2lcDataAdd', component: experiment2lcDataAddComponent },
  { path: 'experiment2emgDataAdd', component: experiment2emgDataAddComponent },
  { path: 'experiment3fpDataAdd', component: experiment3fpDataAddComponent },
  { path: 'experiment3emgDataAdd', component: experiment3emgDataAddComponent },
  { path: 'addPlayer', component: addPlayerComponent },
  { path: 'addScientist', component: addScientistComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
 imports: [RouterModule.forRoot(routes)],
 exports: [RouterModule],
 providers: []
})
export class AppRoutingModule { }
