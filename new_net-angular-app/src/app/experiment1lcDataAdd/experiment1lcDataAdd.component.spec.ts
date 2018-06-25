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

import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as sinon from 'sinon';
import { DataService } from '../data.service';
import { experiment1lcDataAddComponent } from './experiment1lcDataAdd.component';
import {experiment1lcDataAddService} from './experiment1lcDataAdd.service';

describe('experiment1lcDataAddComponent', () => {
  let component: experiment1lcDataAddComponent;
  let fixture: ComponentFixture<experiment1lcDataAddComponent>;

  let mockexperiment1lcDataAddService;
  let mockDataService

  beforeEach(async(() => {

    mockexperiment1lcDataAddService = sinon.createStubInstance(experiment1lcDataAddService);
    mockexperiment1lcDataAddService.getAll.returns([]);
    mockDataService = sinon.createStubInstance(DataService);

    TestBed.configureTestingModule({
      declarations: [ experiment1lcDataAddComponent ],
      imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule
      ],
      providers: [
        {provide: experiment1lcDataAddService, useValue: mockexperiment1lcDataAddService },
        {provide: DataService, useValue: mockDataService },
      ]
    });

    fixture = TestBed.createComponent(experiment1lcDataAddComponent);
    component = fixture.componentInstance;

  }));


  it('should create', () => {
    expect(component).toBeTruthy();
  });

});

