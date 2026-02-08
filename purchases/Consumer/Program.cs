using System;
using Confluent.Kafka;


class Program
{
    static void Main(string[] args)
    {
        var bookingConsumer = new BookingConsumer();
        bookingConsumer.Listen(Console.WriteLine);
    }
}

public interface IBookingConsumer
{
    void Listen(Action message);
}

public class BookingConsumer : IBookingConsumer
{
    public void Listen(Action message)
    {
        const string topic = "purchases";

        var config = new Dictionary<string, string>
        {
            {"group.id", "booking_consumer" },
            {"bootstrap.servers", "localhost:9092" },
            {"enable.auto.commit", "true" },
            //{"AutoOffsetReset", "AutoOffsetReset.Earliest" }
        };

        CancellationTokenSource cts = new CancellationTokenSource();
        Console.CancelKeyPress += (_, e) =>
        {
            e.Cancel = true; // prevent the process from terminating.
            cts.Cancel();
        };

        using (var consumer = new ConsumerBuilder<string, string>(config).Build())
        {
            consumer.Subscribe(topic);
            try
            {
                while (true)
                {
                    var cr = consumer.Consume(cts.Token);
                    string key = cr.Message.Key == null ? "Null" : cr.Message.Key;
                    Console.WriteLine($"Consumed record with key {key} and value {cr.Message.Value}");
                }
            }
            catch (OperationCanceledException)
            {
                //exception might have occurred since Ctrl-C was pressed.
            }
            finally
            {
                // Ensure the consumer leaves the group cleanly and final offsets are committed.
                consumer.Close();
            }
        };
    }




}