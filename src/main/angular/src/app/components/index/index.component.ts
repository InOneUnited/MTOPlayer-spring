import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'mto-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {
  constructor() {}

  elements: any = [
    { id: 1, first: 'Mark', last: 'Otto', handle: '@mdo' },
    { id: 2, first: 'Jacob', last: 'Thornton', handle: '@fat' },
    { id: 3, first: 'Larry', last: 'the Bird', handle: '@twitter' }
  ];

  headElements = ['ID', 'First', 'Last', 'Handle'];

  images = [1, 2, 3].map(
    () => `https://picsum.photos/900/500?random&t=${Math.random()}`
  );

  ngOnInit() {}
}
