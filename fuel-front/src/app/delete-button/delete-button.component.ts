import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FuelService, Purchase } from '../fuel.service';

@Component({
  selector: 'app-delete-button',
  templateUrl: './delete-button.component.html',
  styleUrls: ['./delete-button.component.css']
})
export class DeleteButtonComponent implements OnInit {
  @Input() id!: number;
  @Output() deleteClicked = new EventEmitter<string>();

  constructor(public service: FuelService) { }

  ngOnInit(): void {
  }

  deletePurchase(){
    console.log(`Tämän kohdan id: ` + this.id);
    this.service.deletePurchase(this.id, (json:any)=> {this.deleteClicked.emit('update');})
  }

}
