import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorArticlesComponent } from './vendor-articles.component';

describe('VendorArticlesComponent', () => {
  let component: VendorArticlesComponent;
  let fixture: ComponentFixture<VendorArticlesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VendorArticlesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendorArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
