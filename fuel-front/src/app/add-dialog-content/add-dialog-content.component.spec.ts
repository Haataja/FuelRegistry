import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDialogContentComponent } from './add-dialog-content.component';

describe('AddDialogContentComponent', () => {
  let component: AddDialogContentComponent;
  let fixture: ComponentFixture<AddDialogContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDialogContentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDialogContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
