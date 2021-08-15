import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from './toolbar/toolbar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';

import {MatToolbarModule} from '@angular/material/toolbar';
import { FuelTableComponent } from './fuel-table/fuel-table.component';
import { MatTableModule } from '@angular/material/table';
import { FuelService } from './fuel.service';
import {HttpClientModule} from '@angular/common/http';
import {MatPaginatorModule} from '@angular/material/paginator';
import {Compiler, COMPILER_OPTIONS, CompilerFactory} from '@angular/core';
import {JitCompilerFactory} from '@angular/platform-browser-dynamic';
import {MatSortModule } from '@angular/material/sort';
import { AddButtonComponent} from './add-button/add-button.component';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AddDialogContentComponent } from './add-dialog-content/add-dialog-content.component';
import { DeleteButtonComponent } from './delete-button/delete-button.component';
import { PriceChartComponent } from './price-chart/price-chart.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
export function createCompiler(compilerFactory: CompilerFactory) {
  return compilerFactory.createCompiler();
}

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    FuelTableComponent,
    AddButtonComponent,
    AddDialogContentComponent,
    DeleteButtonComponent,
    PriceChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDialogModule,
    MatToolbarModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatInputModule,
    MatIconModule,
    MatFormFieldModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    NgxChartsModule
  ],
  providers: [FuelService,
            {provide: COMPILER_OPTIONS, useValue: {}, multi: true},
            {provide: CompilerFactory, useClass: JitCompilerFactory, deps: [COMPILER_OPTIONS]},
            {provide: Compiler, useFactory: createCompiler, deps: [CompilerFactory]}],
  bootstrap: [AppComponent]
})
export class AppModule { }
