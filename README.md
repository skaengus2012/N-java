# N-java
This is utility for RxJava. This source was originally part of Ndroid. I separated the part that could only exist in Java.

You can find module in this link.



# Support Function

<B>1. Lambda combination</B> : https://github.com/skaengus2012/Ndroid#lambda-combination<br/>
<B>2. MaybeUtil</B> : https://github.com/skaengus2012/Ndroid#maybeutil<br/>

# Lambda combination

I made builder for lambda because combination method in lambda can use at api >= 24.<br/>
So <B>Ndroid</B> support default combination method using factory & builder pattern.<br/>
Please reference next.

<H2>Predicate Example.</H2>

```java
// a >= 5 && a < 10 || a == 0

IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).
                    				and(a -> a < 10).
                    				or(a -> a == 0).
                   				get();
		    
// !((a >= 5 && a < 10) || (a >= 200 && a < 300))
IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder(
                    LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).and(a -> a < 10).get()).
                    or(LambdaUtil.PredicateBuilder((Integer a) -> a >= 200).and(a -> a < 300).get()).
                    negative().
                    get();
		    
// BiPredicate support!
LambdaUtil.PredicateBuilder((Integer a, Integer b) -> a + b > 0).and((Integer a,Integer b) -> a >= b).get();

```

<H2>Comparator Example</H2>
ComparatorBuilder support <B>null</B> value.<br> We can control null priority using ComparatorBuilder.

```java
List<SubjectRelation> subjectRelationList = Arrays.asList(
                new SubjectRelation(1, 1001, "Doohyun Nam", 1)
                , new SubjectRelation(1, 1002, "Dolkin", 2)
                , new SubjectRelation(1, 1003, "hshawng", 1)
                , new SubjectRelation(1, 1004, "spKwon", 1)
                , new SubjectRelation(2, 1005, "redCamel", 3)
                , new SubjectRelation(2, 1006, "broDuck", 4)
                , new SubjectRelation(3, 1005, null, 3)
        );
	
// order by companySubjectSn, memberName DESC
Observable.fromIterable(subjectRelationList).sorted(
	LambdaUtil.ComparatorBuilder(SubjectRelation::getCompanySubjectSn, (Integer a, Integer b) -> a.compareTo(b)).
		thenComparing(
			LambdaUtil.ComparatorBuilder(SubjectRelation::getMemberName
				, LambdaUtil.ComparatorBuilder((String a, String b) -> a.compareTo(b)).
					nullsFirst().
					reversed())
			).getRx()).
		map(SubjectRelation::getMemberSubjectSn).
		subscribe(System.out::println);
		
```
Check, <B>ComparatorBuilder</B>. That is comparator, which is comparing between member val in Object.<br/>

```java
LambdaUtil.ComparatorBuilder(SubjectRelation::getMemberName,LambdaUtil.
			ComparatorBuilder((String a, String b) -> a.compareTo(b)).
				nullsFirst().
				reversed().
				get());
				
```

<H2>Function Example</H2>

```java
{
	// f(x) = x + 2
	// g(x) = x * 8;
	// f(g(x)) = (x * 8) + 2
	LambdaUtil.FunctionBuilder((Integer a) -> a + 2).
                   compose((Integer a) -> a * 8).
                   get().
                   apply(10);
}

{
	// f(x) = x + 2
	// g(x) = x * 8;
	// g(f(x)) = (x + 2) * 8
	LambdaUtil.FunctionBuilder((Integer a) -> a + 2).
                   andThen((Integer a) -> a * 8).
                   get().
                   apply(10);
}

{
	// f(x, y) = (x * y) + 10
	// g(x) = x * 10
	LambdaUtil.FunctionBuilder((Integer a, Integer b) -> (a * b) + 10).
                   andThen((Integer c) -> c * 10).
                   get().
		   apply(2, 5));
}
```

<H2>Rx Lambda supprt</H2>

I hate RxJava2 lambda because they made method with Exception in functional interface.<br/><br/>
So I support functional interface method not including Exception.<br/><br/>
But Ndroid needed to support <B>Observable in Rx</B>. So I check <B>getRx()</B> for Rx Lambda support.

```java
// a % 5 == 0 && a > 40 || a < 20
Observable.range(0, 60).filter(
                LambdaUtil.PredicateBuilder((Integer a) -> a % 5 == 0).
                        and(a -> a > 40).
                        or(a -> a < 20).
                        getRx()).
                subscribe(System.out::println);
```

So we do not check exception while using lambda. <br/>
Please check <B>Ndroid.appFactory.common.function</B> pakage. you can use simple lambda.<br/><br/>


# MaybeUtil

We can use Maybe instead of Optional in the JAVA8 library.<br/>
But Rx.Maybe is not strong compared java8.optional. (Especially Maybe not support nullableJust.)<br/>
So, I support Rx.Maybe using MaybeUtil.<br/><br/>
Please reference next.

<H2>Null able just Maybe</H2>

```java
String test = "Test", nullValue = null;

// NullAble just support.
MaybeUtil.JustNullable(test).subscribe(System.out::println);

Maybe<String> nullValueMaybe = MaybeUtil.JustNullable(nullValue);

nullValueMaybe.subscribe(System.out::println);
        
// Empty maybe run
MaybeUtil.SubscribeEmpty(nullValueMaybe, () -> System.out.println("That value is null!!"));

// Empty maybe combo
// If maybe is valid, consumer param will execute. But Empty maybe will be emptyRunnable;
MaybeUtil.Subscribe(nullValueMaybe, System.out::println, () -> System.out.println("That value is null!!"));
```

<H2>Map support using maybe!</H2>

```java
HashMap<String, Integer> testMap = new HashMap<>();
testMap.put("key1", 1);
testMap.put("key2", 1);
testMap.put("key3", 1);

// Extract maybe value in Map
ContainerUtil.JustInMap(testMap, "key1").subscribe(System.out::println);

// Run maybe, if targetMap have key-value.
ContainerUtil.RunMaybeInMap(testMap, "key1", System.out::println);
ContainerUtil.RunMaybeInMap(testMap, "key4", System.out::println);
```

<H2>String to boxed object parse support.</H2>

```java
// Parse Integer
StringUtil.ParseInteger("1").subscribe(System.out::println);
StringUtil.ParseInteger("String Text").subscribe(System.out::println);

// Parse Boolean
StringUtil.ParseBoolean("false").subscribe(System.out::println);

// Parse Double
StringUtil.ParseDouble("0.0111111").subscribe(System.out::println);
```


