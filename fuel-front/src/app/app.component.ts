import { Component, ViewChild } from '@angular/core';
import { FuelTableComponent } from './fuel-table/fuel-table.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'fuel-front';
  @ViewChild(FuelTableComponent) table!:FuelTableComponent;

  refreshTable(){
    // console.log(`Catching emitting` + new Date().getTime())
    this.table.refresh();
  }
}
