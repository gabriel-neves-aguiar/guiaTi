import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlataformaCriadorComponent } from './plataforma-criador.component';

describe('PlataformaCriadorComponent', () => {
  let component: PlataformaCriadorComponent;
  let fixture: ComponentFixture<PlataformaCriadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlataformaCriadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlataformaCriadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
