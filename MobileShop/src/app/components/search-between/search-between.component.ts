import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-between',
  templateUrl: './search-between.component.html',
  styleUrls: ['./search-between.component.scss']
})
export class SearchBetweenComponent {
  constructor(private router: Router) { }

  doSearch(value1: string, value2: string) {
    const number1 = parseFloat(value1);
    const number2 = parseFloat(value2);

    this.router.navigateByUrl(`/search/${number1}/${number2}`);
  }
}
