
const R = require('ramda')

const data = [
	{category: 'A', item: 'A01', week: '2018-01', num: 1},
	{category: 'A', item: 'A02', week: '2018-02', num: 1},
	{category: 'C', item: 'C01', week: '2018-04', num: 1},
	{category: 'C', item: 'C01', week: '2018-04', num: 10},
	{category: 'A', item: 'A01', week: '2018-01', num: 1},
	{category: 'A', item: 'A03', week: '2018-03', num: 2},
	{category: 'A', item: 'A03', week: '2018-03', num: 5},
	{category: 'B', item: 'B01', week: '2018-01', num: 1}
]

const min = R.pipe(R.sort(R.comparator(R.lt)), R.head)
const max = R.pipe(R.sort(R.comparator(R.gt)), R.head)

const weeks = R.pluck('week', data)

console.log(weeks)

console.log( min(weeks) )
console.log( max(weeks) )
