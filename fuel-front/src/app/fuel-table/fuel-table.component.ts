import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { FuelService, Purchase } from '../fuel.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-fuel-table',
  templateUrl: './fuel-table.component.html',
  styleUrls: ['./fuel-table.component.css']
})
export class FuelTableComponent implements OnInit {
  displayedColumns: string[] = ['id', 'date', 'mileage', 'litre', 'pricePerLitre','price','location', 'description', 'credit', 'delete'];
  dataSource: MatTableDataSource<Purchase> = new MatTableDataSource<Purchase>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  constructor(private service: FuelService) {
   }

  ngOnInit(): void {
    this.refresh();
  }

  refresh(){
    // console.log(`Refreshing table` + new Date().getTime())
    this.service.getPurchases((j) => this.dataSource.data = j)
  }

  ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }

}
