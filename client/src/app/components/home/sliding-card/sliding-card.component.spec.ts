import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SlidingCardComponent } from './sliding-card.component';

describe('SlidingCardComponent', () => {
  let component: SlidingCardComponent;
  let fixture: ComponentFixture<SlidingCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SlidingCardComponent]
    });
    fixture = TestBed.createComponent(SlidingCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
