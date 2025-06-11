import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconeExpandirComponent } from './icone-expandir.component';

describe('IconeExpandirComponent', () => {
  let component: IconeExpandirComponent;
  let fixture: ComponentFixture<IconeExpandirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconeExpandirComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconeExpandirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
