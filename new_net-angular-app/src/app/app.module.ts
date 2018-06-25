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

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { DataService } from './data.service';
import { AppComponent } from './app.component';
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

  @NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PlayerComponent,
    Experiment1lcComponent,
    Experiment2lcComponent,
    Experiment2emgComponent,
    Experiment3fpComponent,
    Experiment3emgComponent,
    ScientistComponent,
    experiment1lcDataAddComponent,
    experiment2lcDataAddComponent,
    experiment2emgDataAddComponent,
    experiment3fpDataAddComponent,
    experiment3emgDataAddComponent,
    addPlayerComponent,
    addScientistComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
