import {
  ApolloClient,
  createHttpLink,
  InMemoryCache
} from '@apollo/client';

// 2
const httpLink = createHttpLink({
  uri: 'http://localhost:8080/graphql'
});

// 3
export const client = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache()
});